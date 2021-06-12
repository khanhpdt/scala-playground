package vn.khanhpdt.playgrounds.prop_based_testing

import vn.khanhpdt.playgrounds.prop_based_testing.Prop.{FailedCase, SuccessCount}

trait Prop {
  def check: Either[(FailedCase, SuccessCount), SuccessCount]
  def &&(other: Prop): Either[(FailedCase, SuccessCount), SuccessCount]
}

object Prop {
  type SuccessCount = Int
  type FailedCase = String
}
