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
package org.openehealth.ipf.commons.lbs

import org.openehealth.ipf.commons.message.Converter

class StoreDataSourceListConversion {
    InputStream convertToStream(StoreDataSourceList msg) {
        def streams = [Converter.convertToStream(msg.size())]
        streams += msg.collect { Converter.convertToStream(it) }
        Converter.join(streams)
    }

    StoreDataSourceList convertToMsg(InputStream inputStream, StoreDataSourceList dummy) {
        def list = new StoreDataSourceList()
        def size = Converter.convertToMsg(inputStream, Integer)
        (1..size).each {
            list += Converter.convertToMsg(inputStream, StoreDataSource)
        }
        list
    }
}
