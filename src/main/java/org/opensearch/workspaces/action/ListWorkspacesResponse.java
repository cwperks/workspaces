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
import org.opensearch.core.action.ActionResponse;
import org.opensearch.core.common.io.stream.StreamInput;
import org.opensearch.core.common.io.stream.StreamOutput;
import org.opensearch.core.xcontent.ToXContentObject;
import org.opensearch.core.xcontent.XContentBuilder;
import org.opensearch.workspaces.model.Workspace;

public class ListWorkspacesResponse extends ActionResponse implements ToXContentObject {
  private final List<Workspace> workspaces;

  public ListWorkspacesResponse(List<Workspace> workspaces) {
    this.workspaces = workspaces;
  }

  public ListWorkspacesResponse(StreamInput in) throws IOException {
    super(in);
    workspaces = in.readList(Workspace::new);
  }

  @Override
  public void writeTo(StreamOutput out) throws IOException {
    out.writeList(workspaces);
  }

  @Override
  public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
    builder.startObject().startArray("workspaces");
    for (Workspace ws : workspaces) {
      ws.toXContent(builder, params);
    }
    return builder.endArray().endObject();
  }
}
