package object nullable {
  implicit final class RichOption[T <: AnyRef](val option: Option[T]) extends AnyVal {
    def toNullable = option match {
      case Some(x) => Nullable(x)
      case None => Nullable(null)
    }
  }
}
