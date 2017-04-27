/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.spark.deploy.kubernetes.integrationtest.backend.GCE

import io.fabric8.kubernetes.client.{ConfigBuilder, DefaultKubernetesClient}

import org.apache.spark.deploy.kubernetes.config.resolveK8sMaster
import org.apache.spark.deploy.kubernetes.integrationtest.backend.IntegrationTestBackend
import org.apache.spark.deploy.kubernetes.integrationtest.constants.GCE_TEST_BACKEND

class GCETestBackend extends IntegrationTestBackend {
  val master = System.getProperty("spark.kubernetes.test.master")
  var k8ConfBuilder = new ConfigBuilder()
    .withApiVersion("v1")
    .withMasterUrl(resolveK8sMaster(master))
  val k8ClientConfig = k8ConfBuilder.build
  val defaultClient = new DefaultKubernetesClient(k8ClientConfig)

  override def getKubernetesClient: DefaultKubernetesClient = {
    return defaultClient
  }

  override def cleanUp: Unit = {}
  override def name: String = GCE_TEST_BACKEND
}
