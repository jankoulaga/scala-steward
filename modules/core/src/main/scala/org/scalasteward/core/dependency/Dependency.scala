/*
 * Copyright 2018 scala-steward contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.scalasteward.core.dependency

import io.circe.generic.semiauto._
import io.circe.{Decoder, Encoder}
import org.scalasteward.core.model.Update
import org.scalasteward.core.sbt.data.SbtVersion
import org.scalasteward.core.util.Nel

final case class Dependency(
    groupId: String,
    artifactId: String,
    artifactIdCross: String,
    version: String,
    sbtVersion: Option[SbtVersion] = None
) {
  def formatAsModuleId: String =
    s""""$groupId" % "$artifactIdCross" % "$version""""

  def toUpdate: Update.Single =
    Update.Single(groupId, artifactId, version, Nel.of(version))
}

object Dependency {
  implicit val dependencyDecoder: Decoder[Dependency] =
    deriveDecoder

  implicit val dependencyEncoder: Encoder[Dependency] =
    deriveEncoder
}