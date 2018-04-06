Dear reader,
In the following project you are able to see implemented test automation framework based on
Java/Cucumber, Page Object Model and Page Factory.
In order to run tests on PhantomJS, you should specify 'true' in 'integration-test.properties' file.
Otherwise tests will run in Firefox Browser, please ensure it is installed on the machine in case of running.
With FF run the advertisement windows will be shown and handled, with Phantom there won't be any advertisement,
So Cucumber test step dedicated to advertisement handling should be skipped for PhantomJS.

In order not to spend to much time on the task I ignored implementation and configuration of error logger,
event listener and Firefox profile configs, hope it is not important for the task evaluation.

While running tests I decided to start directly with Search page, not Home page, though I left Home page implemented.
As far as Step Definition class cannot extend any class then I called BaseTest class inside Step Definition class.

Thank you for your attention,
Andrii