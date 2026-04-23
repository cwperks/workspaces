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
import org.opensearch.core.action.ActionResponse;
import org.opensearch.core.common.io.stream.StreamInput;
import org.opensearch.core.common.io.stream.StreamOutput;
import org.opensearch.core.xcontent.ToXContentObject;
import org.opensearch.core.xcontent.XContentBuilder;
import org.opensearch.workspaces.model.Workspace;

public class UpdateWorkspaceResponse extends ActionResponse implements ToXContentObject {
  private final Workspace workspace;

  public UpdateWorkspaceResponse(Workspace workspace) {
    this.workspace = workspace;
  }

  public UpdateWorkspaceResponse(StreamInput in) throws IOException {
    super(in);
    workspace = new Workspace(in);
  }

  @Override
  public void writeTo(StreamOutput out) throws IOException {
    workspace.writeTo(out);
  }

  @Override
  public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
    return builder.startObject().field("workspace", workspace).endObject();
  }
}
