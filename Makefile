# Define variables
MVN = mvn
COVERAGE_REPORT_DIR = target/site/jacoco
COVERAGE_REPORT = $(COVERAGE_REPORT_DIR)/index.html

# Define default target
all: test

# Target to run tests
test:
	@$(MVN) clean test

# Target to run tests with code coverage
coverage:
	@$(MVN) clean verify jacoco:report
	@echo "Coverage report generated at $(COVERAGE_REPORT)"

# Target to run only unit tests
unit-tests:
	@$(MVN) clean test

# Target to run only integration tests
integration-tests:
	@$(MVN) clean verify -DskipTests=false -DskipIntegrationTests=false

# Target to clean the project
clean:
	@$(MVN) clean

# Target to compile the project
compile:
	@$(MVN) compile

# Target to install dependencies
install:
	@$(MVN) install

# Target to generate the site (including coverage report)
site:
	@$(MVN) site

# Target to display help
help:
	@echo "Makefile commands:"
	@echo "  all                - Run all tests"
	@echo "  test               - Run unit tests"
	@echo "  coverage           - Run tests and generate coverage report"
	@echo "  unit-tests         - Run only unit tests"
	@echo "  integration-tests  - Run integration tests"
	@echo "  clean              - Clean the project"
	@echo "  compile            - Compile the project"
	@echo "  install            - Install dependencies"
	@echo "  site               - Generate the site"
	@echo "  help               - Show this help message"
