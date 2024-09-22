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

package org.apache.seatunnel.engine.server.rest.servlet;

import org.apache.seatunnel.api.common.metrics.JobMetrics;
import org.apache.seatunnel.engine.common.Constant;
import org.apache.seatunnel.engine.core.job.JobDAGInfo;
import org.apache.seatunnel.engine.core.job.JobInfo;
import org.apache.seatunnel.engine.server.master.JobHistoryService.JobState;
import org.apache.seatunnel.engine.server.rest.RestConstant;

import com.hazelcast.instance.impl.HazelcastInstanceImpl;
import com.hazelcast.internal.json.JsonObject;
import com.hazelcast.map.IMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.apache.seatunnel.engine.server.rest.RestHttpGetCommandProcessor.getJobInfoJson;

public class JobInfoServlet extends BaseServlet {
    public JobInfoServlet(HazelcastInstanceImpl hazelcastInstance) {
        super(hazelcastInstance);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String uri = req.getRequestURI();

        String jobId = uri.substring(uri.lastIndexOf("/") + 1);

        IMap<Object, Object> jobInfoMap = hazelcastInstance.getMap(Constant.IMAP_RUNNING_JOB_INFO);
        JobInfo jobInfo = (JobInfo) jobInfoMap.get(Long.valueOf(jobId));
        IMap<Object, Object> finishedJobStateMap =
                hazelcastInstance.getMap(Constant.IMAP_FINISHED_JOB_STATE);
        JobState finishedJobState = (JobState) finishedJobStateMap.get(Long.valueOf(jobId));
        if (!jobId.isEmpty() && jobInfo != null) {
            writeJson(resp, convertToJson(jobInfo, Long.parseLong(jobId)));
        } else if (!jobId.isEmpty() && finishedJobState != null) {
            JobMetrics finishedJobMetrics =
                    (JobMetrics)
                            hazelcastInstance
                                    .getMap(Constant.IMAP_FINISHED_JOB_METRICS)
                                    .get(Long.valueOf(jobId));
            JobDAGInfo finishedJobDAGInfo =
                    (JobDAGInfo)
                            hazelcastInstance
                                    .getMap(Constant.IMAP_FINISHED_JOB_VERTEX_INFO)
                                    .get(Long.valueOf(jobId));
            writeJson(
                    resp,
                    getJobInfoJson(
                            finishedJobState,
                            finishedJobMetrics.toJsonString(),
                            finishedJobDAGInfo));
        } else {
            writeJson(resp, new JsonObject().add(RestConstant.JOB_ID, jobId));
        }
    }
}
