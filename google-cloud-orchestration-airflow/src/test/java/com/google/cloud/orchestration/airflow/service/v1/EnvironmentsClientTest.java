/*
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.orchestration.airflow.service.v1;

import static com.google.cloud.orchestration.airflow.service.v1.EnvironmentsClient.ListEnvironmentsPagedResponse;

import com.google.api.gax.core.NoCredentialsProvider;
import com.google.api.gax.grpc.GaxGrpcProperties;
import com.google.api.gax.grpc.testing.LocalChannelProvider;
import com.google.api.gax.grpc.testing.MockGrpcService;
import com.google.api.gax.grpc.testing.MockServiceHelper;
import com.google.api.gax.rpc.ApiClientHeaderProvider;
import com.google.api.gax.rpc.InvalidArgumentException;
import com.google.api.gax.rpc.StatusCode;
import com.google.common.collect.Lists;
import com.google.longrunning.Operation;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.Any;
import com.google.protobuf.Empty;
import com.google.protobuf.FieldMask;
import com.google.protobuf.Timestamp;
import io.grpc.StatusRuntimeException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import javax.annotation.Generated;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

@Generated("by gapic-generator-java")
public class EnvironmentsClientTest {
  private static MockEnvironments mockEnvironments;
  private static MockServiceHelper mockServiceHelper;
  private LocalChannelProvider channelProvider;
  private EnvironmentsClient client;

  @BeforeClass
  public static void startStaticServer() {
    mockEnvironments = new MockEnvironments();
    mockServiceHelper =
        new MockServiceHelper(
            UUID.randomUUID().toString(), Arrays.<MockGrpcService>asList(mockEnvironments));
    mockServiceHelper.start();
  }

  @AfterClass
  public static void stopServer() {
    mockServiceHelper.stop();
  }

  @Before
  public void setUp() throws IOException {
    mockServiceHelper.reset();
    channelProvider = mockServiceHelper.createChannelProvider();
    EnvironmentsSettings settings =
        EnvironmentsSettings.newBuilder()
            .setTransportChannelProvider(channelProvider)
            .setCredentialsProvider(NoCredentialsProvider.create())
            .build();
    client = EnvironmentsClient.create(settings);
  }

  @After
  public void tearDown() throws Exception {
    client.close();
  }

  @Test
  public void createEnvironmentTest() throws Exception {
    Environment expectedResponse =
        Environment.newBuilder()
            .setName("name3373707")
            .setConfig(EnvironmentConfig.newBuilder().build())
            .setUuid("uuid3601339")
            .setCreateTime(Timestamp.newBuilder().build())
            .setUpdateTime(Timestamp.newBuilder().build())
            .putAllLabels(new HashMap<String, String>())
            .build();
    Operation resultOperation =
        Operation.newBuilder()
            .setName("createEnvironmentTest")
            .setDone(true)
            .setResponse(Any.pack(expectedResponse))
            .build();
    mockEnvironments.addResponse(resultOperation);

    String parent = "parent-995424086";
    Environment environment = Environment.newBuilder().build();

    Environment actualResponse = client.createEnvironmentAsync(parent, environment).get();
    Assert.assertEquals(expectedResponse, actualResponse);

    List<AbstractMessage> actualRequests = mockEnvironments.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    CreateEnvironmentRequest actualRequest = ((CreateEnvironmentRequest) actualRequests.get(0));

    Assert.assertEquals(parent, actualRequest.getParent());
    Assert.assertEquals(environment, actualRequest.getEnvironment());
    Assert.assertTrue(
        channelProvider.isHeaderSent(
            ApiClientHeaderProvider.getDefaultApiClientHeaderKey(),
            GaxGrpcProperties.getDefaultApiClientHeaderPattern()));
  }

  @Test
  public void createEnvironmentExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(io.grpc.Status.INVALID_ARGUMENT);
    mockEnvironments.addException(exception);

    try {
      String parent = "parent-995424086";
      Environment environment = Environment.newBuilder().build();
      client.createEnvironmentAsync(parent, environment).get();
      Assert.fail("No exception raised");
    } catch (ExecutionException e) {
      Assert.assertEquals(InvalidArgumentException.class, e.getCause().getClass());
      InvalidArgumentException apiException = ((InvalidArgumentException) e.getCause());
      Assert.assertEquals(StatusCode.Code.INVALID_ARGUMENT, apiException.getStatusCode().getCode());
    }
  }

  @Test
  public void getEnvironmentTest() throws Exception {
    Environment expectedResponse =
        Environment.newBuilder()
            .setName("name3373707")
            .setConfig(EnvironmentConfig.newBuilder().build())
            .setUuid("uuid3601339")
            .setCreateTime(Timestamp.newBuilder().build())
            .setUpdateTime(Timestamp.newBuilder().build())
            .putAllLabels(new HashMap<String, String>())
            .build();
    mockEnvironments.addResponse(expectedResponse);

    String name = "name3373707";

    Environment actualResponse = client.getEnvironment(name);
    Assert.assertEquals(expectedResponse, actualResponse);

    List<AbstractMessage> actualRequests = mockEnvironments.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    GetEnvironmentRequest actualRequest = ((GetEnvironmentRequest) actualRequests.get(0));

    Assert.assertEquals(name, actualRequest.getName());
    Assert.assertTrue(
        channelProvider.isHeaderSent(
            ApiClientHeaderProvider.getDefaultApiClientHeaderKey(),
            GaxGrpcProperties.getDefaultApiClientHeaderPattern()));
  }

  @Test
  public void getEnvironmentExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(io.grpc.Status.INVALID_ARGUMENT);
    mockEnvironments.addException(exception);

    try {
      String name = "name3373707";
      client.getEnvironment(name);
      Assert.fail("No exception raised");
    } catch (InvalidArgumentException e) {
      // Expected exception.
    }
  }

  @Test
  public void listEnvironmentsTest() throws Exception {
    Environment responsesElement = Environment.newBuilder().build();
    ListEnvironmentsResponse expectedResponse =
        ListEnvironmentsResponse.newBuilder()
            .setNextPageToken("")
            .addAllEnvironments(Arrays.asList(responsesElement))
            .build();
    mockEnvironments.addResponse(expectedResponse);

    String parent = "parent-995424086";

    ListEnvironmentsPagedResponse pagedListResponse = client.listEnvironments(parent);

    List<Environment> resources = Lists.newArrayList(pagedListResponse.iterateAll());

    Assert.assertEquals(1, resources.size());
    Assert.assertEquals(expectedResponse.getEnvironmentsList().get(0), resources.get(0));

    List<AbstractMessage> actualRequests = mockEnvironments.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    ListEnvironmentsRequest actualRequest = ((ListEnvironmentsRequest) actualRequests.get(0));

    Assert.assertEquals(parent, actualRequest.getParent());
    Assert.assertTrue(
        channelProvider.isHeaderSent(
            ApiClientHeaderProvider.getDefaultApiClientHeaderKey(),
            GaxGrpcProperties.getDefaultApiClientHeaderPattern()));
  }

  @Test
  public void listEnvironmentsExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(io.grpc.Status.INVALID_ARGUMENT);
    mockEnvironments.addException(exception);

    try {
      String parent = "parent-995424086";
      client.listEnvironments(parent);
      Assert.fail("No exception raised");
    } catch (InvalidArgumentException e) {
      // Expected exception.
    }
  }

  @Test
  public void updateEnvironmentTest() throws Exception {
    Environment expectedResponse =
        Environment.newBuilder()
            .setName("name3373707")
            .setConfig(EnvironmentConfig.newBuilder().build())
            .setUuid("uuid3601339")
            .setCreateTime(Timestamp.newBuilder().build())
            .setUpdateTime(Timestamp.newBuilder().build())
            .putAllLabels(new HashMap<String, String>())
            .build();
    Operation resultOperation =
        Operation.newBuilder()
            .setName("updateEnvironmentTest")
            .setDone(true)
            .setResponse(Any.pack(expectedResponse))
            .build();
    mockEnvironments.addResponse(resultOperation);

    String name = "name3373707";
    Environment environment = Environment.newBuilder().build();
    FieldMask updateMask = FieldMask.newBuilder().build();

    Environment actualResponse = client.updateEnvironmentAsync(name, environment, updateMask).get();
    Assert.assertEquals(expectedResponse, actualResponse);

    List<AbstractMessage> actualRequests = mockEnvironments.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    UpdateEnvironmentRequest actualRequest = ((UpdateEnvironmentRequest) actualRequests.get(0));

    Assert.assertEquals(name, actualRequest.getName());
    Assert.assertEquals(environment, actualRequest.getEnvironment());
    Assert.assertEquals(updateMask, actualRequest.getUpdateMask());
    Assert.assertTrue(
        channelProvider.isHeaderSent(
            ApiClientHeaderProvider.getDefaultApiClientHeaderKey(),
            GaxGrpcProperties.getDefaultApiClientHeaderPattern()));
  }

  @Test
  public void updateEnvironmentExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(io.grpc.Status.INVALID_ARGUMENT);
    mockEnvironments.addException(exception);

    try {
      String name = "name3373707";
      Environment environment = Environment.newBuilder().build();
      FieldMask updateMask = FieldMask.newBuilder().build();
      client.updateEnvironmentAsync(name, environment, updateMask).get();
      Assert.fail("No exception raised");
    } catch (ExecutionException e) {
      Assert.assertEquals(InvalidArgumentException.class, e.getCause().getClass());
      InvalidArgumentException apiException = ((InvalidArgumentException) e.getCause());
      Assert.assertEquals(StatusCode.Code.INVALID_ARGUMENT, apiException.getStatusCode().getCode());
    }
  }

  @Test
  public void deleteEnvironmentTest() throws Exception {
    Empty expectedResponse = Empty.newBuilder().build();
    Operation resultOperation =
        Operation.newBuilder()
            .setName("deleteEnvironmentTest")
            .setDone(true)
            .setResponse(Any.pack(expectedResponse))
            .build();
    mockEnvironments.addResponse(resultOperation);

    String name = "name3373707";

    client.deleteEnvironmentAsync(name).get();

    List<AbstractMessage> actualRequests = mockEnvironments.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    DeleteEnvironmentRequest actualRequest = ((DeleteEnvironmentRequest) actualRequests.get(0));

    Assert.assertEquals(name, actualRequest.getName());
    Assert.assertTrue(
        channelProvider.isHeaderSent(
            ApiClientHeaderProvider.getDefaultApiClientHeaderKey(),
            GaxGrpcProperties.getDefaultApiClientHeaderPattern()));
  }

  @Test
  public void deleteEnvironmentExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(io.grpc.Status.INVALID_ARGUMENT);
    mockEnvironments.addException(exception);

    try {
      String name = "name3373707";
      client.deleteEnvironmentAsync(name).get();
      Assert.fail("No exception raised");
    } catch (ExecutionException e) {
      Assert.assertEquals(InvalidArgumentException.class, e.getCause().getClass());
      InvalidArgumentException apiException = ((InvalidArgumentException) e.getCause());
      Assert.assertEquals(StatusCode.Code.INVALID_ARGUMENT, apiException.getStatusCode().getCode());
    }
  }
}
