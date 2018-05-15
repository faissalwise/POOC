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

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 * @author
 */
public class PoliciesWriter implements ItemWriter<ExamResult> {

	private static Logger log = LoggerFactory.getLogger(PoliciesWriter.class);

	private SimpleJdbcInsert policiesInsert;
	private JdbcTemplate accountUpdate;

	public PoliciesWriter(DataSource dataSource) {
		// paymentInsert = new
		// SimpleJdbcInsert(dataSource).withTableName("PAYMENTS").usingColumns("RECIPIENT",
		// "PAYEE", "AMOUNT", "PAY_DATE");
		// accountUpdate = new JdbcTemplate(dataSource);
	}

	@Override
	public void write(List<? extends ExamResult> policies) throws Exception {
		log.info("Writer Policies");

		// for (Payment payment : payments) {
		// MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		// parameterSource.addValue("RECIPIENT",
		// payment.getDestinationAccountNo()).addValue("PAYEE",
		// payment.getSourceAccountNo())
		// .addValue("AMOUNT", payment.getAmount()).addValue("DATE", payment.getDate());
		// accountUpdate.update("UPDATE ACCOUNTS SET BALANCE = BALANCE + ? WHERE ID =
		// ?", payment.getAmount(), payment.getDestinationAccountNo());
		// accountUpdate.update("UPDATE ACCOUNTS SET BALANCE = BALANCE - ? WHERE ID =
		// ?", payment.getAmount(), payment.getSourceAccountNo());
		// paymentInsert.execute(parameterSource);
		// System.out.println("Executing the step " + payment.getDate());
		// }
	}
}
