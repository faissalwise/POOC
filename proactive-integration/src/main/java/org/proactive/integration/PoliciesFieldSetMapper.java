/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.proactive.integration;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 * @author 
 */
public class PoliciesFieldSetMapper implements FieldSetMapper<ExamResult> {

    @Override
    public ExamResult mapFieldSet(FieldSet fieldSet) throws BindException {
    	ExamResult payment = new ExamResult();

//       payment.setSourceAccountNo(fieldSet.readString("source"));
//       payment.setDestinationAccountNo(fieldSet.readString("destination"));
//       payment.setAmount(fieldSet.readBigDecimal("amount"));
//       payment.setDate(fieldSet.readDate("date"));

       return null;
    }
}
