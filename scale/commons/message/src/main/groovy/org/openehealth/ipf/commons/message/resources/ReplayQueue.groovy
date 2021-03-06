/*
 * Copyright 2009 the original author or authors.
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
package org.openehealth.ipf.commons.message.resources

import javax.annotation.Resource
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import org.openehealth.ipf.commons.utils.json.JSONSupport
import org.openehealth.ipf.commons.message.MessageStore
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope("singleton")
@Path('/replayQueue')
@Produces('text/plain')
class ReplayQueue {
    @Resource def MessageStore messageStore
    def json = new JSONSupport()

    @POST
    @Consumes('application/json')
    void postMessages(String msgIdsJson) {
        def msgIds = json.parseList(msgIdsJson, String)
        msgIds.each { messageStore.replayMessage(it) }
    }

    @POST
    @Consumes('plain/text')
    void postMessage(String msgId) {
        messageStore.replayMessage(msgId)
    }
}
