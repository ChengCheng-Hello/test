Feature: 登录测试
	Scenario: 使用正确手机号，正确密码登录
		Then I wait for the view with id "btn_login" to appear
		Then I press view with id "btn_login"
		Then I see "账号"
		Then I enter "13241967851" into input field number 1
		Then I enter "cc123456" into input field number 2
		Then I press view with id "tv_login"
		Then I wait up to 20 seconds to see "校区名片"
		Then I take a screenshot
	Scenario: 使用正确手机号，错误密码登录
		Then I wait for the view with id "btn_login" to appear
		Then I press view with id "btn_login"
		Then I see "账号"
		Then I clear input field number 1
		Then I clear input field number 2
		Then I enter "13241967851" into input field number 1
		Then I enter "nn123456" into input field number 2
		Then I press view with id "tv_login"
		Then I wait up to 20 seconds to see "帐号或密码错误，请重新输入"
		Then I take a screenshot
	Scenario: 使用正确手机号，密码登录
		Then I pass "login" scenario
