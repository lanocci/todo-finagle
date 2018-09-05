package todofinagle.model

trait TodoReadable {
  def resolve(ids: Iterable[TodoId]): Itrerable[Todo]
  def resolveAll(): Iterable[Todo]
}

trait TodoWritable {
  def create(todo: Todo): Todo
  def update(todo: Todo): Todo
}

trait TodoRepository extends TodoReadable with TodoWritable