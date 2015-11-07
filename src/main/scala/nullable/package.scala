package object nullable {
  implicit final class RichOption[T >: Null](val option: Option[T]) extends AnyVal {
    def toNullable: Nullable[T] = option match {
      case Some(x) => Nullable(x)
      case None => Null
    }
  }

  @inline
  val Null: Nullable[Null] = Nullable(null)
}
