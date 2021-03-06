/*
 * Copyright 2013 Sławomir Śledź <slawomir.sledz@sof-tech.pl>.
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
package pl.softech.eav.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import pl.softech.eav.domain.dsl.SymbolTable;
import pl.softech.eav.domain.object.MyObject;

/**
 * @author Sławomir Śledź <slawomir.sledz@sof-tech.pl>
 * @since 1.2
 */
public class Example {
	public static void main(String[] args) throws Exception {

		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class)) {

			BootstrapperService bootstrapper = ctx.getBean(BootstrapperService.class);
			
			bootstrapper.onApplicationStart();
			
			DocumentManagementService documentManagementService = ctx.getBean(DocumentManagementService.class);

			SymbolTable symbolTable = documentManagementService.loadConfigurationFromFile("cv-scheme.eav");
			
			MyObject cv = symbolTable.getObject("ssledz-cv");
			
			System.out.println(cv.toString());

		}

	}
}
