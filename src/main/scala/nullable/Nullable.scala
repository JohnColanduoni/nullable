package nullable

import scala.language.implicitConversions

final class Nullable[+A <: AnyRef](val value: A) extends AnyVal {
  @inline
  def isEmpty = value eq null

  @inline
  def isDefined = !isEmpty

  def get: A =
    if(isDefined)
      value
    else
      throw new NoSuchElementException("This object is null.")

  @inline
  def getOrElse[B >: A](default: => B): B =
    if(isEmpty)
      default
    else
      value

  @inline
  def map[B <: AnyRef](f: A => B): Nullable[B] =
    if(isEmpty)
      Null
    else
      Nullable[B](f(value))

  @inline
  def fold[B](ifEmpty: => B)(f: A => B): B =
    if(isEmpty)
      ifEmpty
    else
      f(value)

  @inline
  def flatMap[B <: AnyRef](f: A => Nullable[B]): Nullable[B] =
    if(isEmpty)
      Nullable(null.asInstanceOf[B])
    else
      f(value)

  @inline
  def filter(p: A => Boolean): Nullable[A] =
    if(isEmpty || p(value))
      this
    else
      Null

  @inline
  def filterNot(p: A => Boolean): Nullable[A] =
    if(isEmpty || !p(value))
      this
    else
      Null

  def nonEmpty = isDefined

  @inline
  def contains[A1 >: A](elem: A1) =
    !isEmpty && value == elem

  @inline
  def exists(p: A => Boolean) =
    !isEmpty && p(value)

  @inline
  def forAll(p: A => Boolean) =
    isEmpty || p(value)

  @inline
  def foreach[U](f: A => U): Unit =
    if(isDefined)
      f(value)

  @inline
  def collect[B <: AnyRef](pf: PartialFunction[A, B]): Nullable[B] =
    if(isDefined)
      Nullable[B](pf.applyOrElse[A, B](value, x => null.asInstanceOf[B]))
    else
      Null

  @inline
  def orElse[B >: A <: AnyRef](alternative: => Nullable[B]): Nullable[B]  =
    if(isDefined)
      this
    else
      alternative

  def iterator: Iterator[A] =
    if(isEmpty)
      collection.Iterator.empty
    else
      collection.Iterator.single(value)

  def toList: List[A] =
    if(isEmpty)
      Nil
    else
      value :: Nil

  @inline
  def toRight[L](left: => L): Either[L, A] =
    if(isDefined)
      Right(value)
    else
      Left(left)

  @inline
  def toLeft[R](right: => R): Either[A, R] =
    if(isDefined)
      Left(value)
    else
      Right(right)

  @inline
  def toOption: Option[A] =
    if(isDefined)
      Some(value)
    else
      None
}

object Nullable {
  def apply[T <: AnyRef](value: T) = new Nullable(value)

  implicit def nullMarkerToNull[A <: AnyRef](x: Null.type): Nullable[A] = new Nullable(null.asInstanceOf[A])
}

object Null {
  def unapply[T <: AnyRef](x: Nullable[T]) = x.isEmpty
}

object NotNull {
  def unapply[T <: AnyRef](x: Nullable[T]) = x
}