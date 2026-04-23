/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

package org.opensearch.workspaces.action;

import java.io.IOException;
import java.util.List;
import org.opensearch.action.ActionRequest;
import org.opensearch.action.ActionRequestValidationException;
import org.opensearch.core.common.io.stream.StreamInput;
import org.opensearch.core.common.io.stream.StreamOutput;

public class UpdateWorkspaceRequest extends ActionRequest {
  private final String workspaceId;
  private final String name;
  private final String description;
  private final List<String> features;
  private final String color;

  public UpdateWorkspaceRequest(
      String workspaceId, String name, String description, List<String> features, String color) {
    this.workspaceId = workspaceId;
    this.name = name;
    this.description = description;
    this.features = features;
    this.color = color;
  }

  public UpdateWorkspaceRequest(StreamInput in) throws IOException {
    super(in);
    workspaceId = in.readString();
    name = in.readString();
    description = in.readOptionalString();
    features = in.readStringList();
    color = in.readOptionalString();
  }

  public String getWorkspaceId() {
    return workspaceId;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public List<String> getFeatures() {
    return features;
  }

  public String getColor() {
    return color;
  }

  @Override
  public ActionRequestValidationException validate() {
    return null;
  }

  @Override
  public void writeTo(StreamOutput out) throws IOException {
    super.writeTo(out);
    out.writeString(workspaceId);
    out.writeString(name);
    out.writeOptionalString(description);
    out.writeStringCollection(features);
    out.writeOptionalString(color);
  }
}
