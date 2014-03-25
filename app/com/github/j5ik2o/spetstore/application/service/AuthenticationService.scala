package com.github.j5ik2o.spetstore.application.service

import com.github.j5ik2o.spetstore.application.EntityIOContextProvider
import com.google.inject.{Inject, Singleton}
import scala.util.Try
import com.github.j5ik2o.spetstore.domain.lifecycle.customer.CustomerRepository

@Singleton
class AuthenticationService @Inject()
(val customerRepository: CustomerRepository,
 val entityIOContextProvider: EntityIOContextProvider) {

  implicit val ctx = entityIOContextProvider.get

  def authentication(loginName: String, password: String): Try[Boolean] = {
    customerRepository.resolveByLoginName(loginName).map {
      customer =>
        customer.exists(_.config.password == password)
    }
  }

}
