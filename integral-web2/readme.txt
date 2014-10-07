Preparing a new Grunt project:
step1: install node.js from http://nodejs.org/download/

step2: install ruby from http://rubyinstaller.org/
Note: Tick the "Add Ruby executables to your PATH"

step3: install git from http://git-scm.com/downloads
step3.1 add the following to the Environment Variables -> System Variable "PATH"

;C:\Program Files (x86)\Git\bin;C:\Program Files (x86)\Git\cmd


step4: install compass with this command 
	gem install sass --version "3.2.10"

step5: install compass with this command 
	gem install compass --version "0.12.2"
Reference: http://thesassway.com/beginner/getting-started-with-sass-and-compass

Note: Have use version 0.12.2 compass installer because there is no lastest version compass plugin in integral-web2 compatible with the latest version compass windown intaller.
http://stackoverflow.com/questions/22597223/errnoeaccess-permission-denied-running-compass-watch

step6: npm install -g grunt
step7: npm install -g bower
step8: npm install -g yo
Note: cd to integral-web2 project
step9: npm install

step10: bower install
Step10.1: refer to integral-web2/bower.json to check for the angular-bootstrap/jquery version when being prompt for version selection

step11: grunt serve

To install new plugin:
step1: bower install <plugin-name> --save
step2: update dependencies (node_modules\.bin\bower-requirejs -c app\scripts\main.js)
step3: make the the folder deleted from bower_components
step4: load the module in app.js

To uninstall plugin
step1: bower uninstall <plugin-name> -- save

To run project:
grunt serve


Grunt-bower command:
npm install
grunt serve
bower update (update plugins)
bower install <plugin-name> --save
npm update
npm install -g karma

node_modules\.bin\bower-requirejs -c app\scripts\main.js (*update dependencies)

copy angular-ui-router.js into bower_components/angular-ui-router/release

app-project source
test - project test
karma start

go to angular line for error and console.log the error

Tutorial http://www.sitepoint.com/kickstart-your-angularjs-development-with-yeoman-grunt-and-bower/

   
   
https://github.com/trecloux/yeoman-maven-plugin