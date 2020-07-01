package com.lubo.domain

import scala.collection.immutable

object Domain {
  final case class User(name: String, age: Int, countryOfResidence: String)
  final case class Users(users: immutable.Seq[User])
}
