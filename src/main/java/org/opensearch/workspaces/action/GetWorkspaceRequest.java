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
import org.opensearch.action.ActionRequest;
import org.opensearch.action.ActionRequestValidationException;
import org.opensearch.core.common.io.stream.StreamInput;
import org.opensearch.core.common.io.stream.StreamOutput;

public class GetWorkspaceRequest extends ActionRequest {
  private final String workspaceId;

  public GetWorkspaceRequest(String workspaceId) {
    this.workspaceId = workspaceId;
  }

  public GetWorkspaceRequest(StreamInput in) throws IOException {
    super(in);
    workspaceId = in.readString();
  }

  public String getWorkspaceId() {
    return workspaceId;
  }

  @Override
  public ActionRequestValidationException validate() {
    return null;
  }

  @Override
  public void writeTo(StreamOutput out) throws IOException {
    super.writeTo(out);
    out.writeString(workspaceId);
  }
}
