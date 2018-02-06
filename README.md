## Test

### Calabash-Android
---
> 
- 行为驱动开发（Behavior-driven development，缩写BDD）是一种敏捷软件开发的技术。它通过用自然语言书写非程序员可读的测试用例扩展了测试驱动开发方法。
- 跨平台

#### 安装
参考官网[说明文档](https://github.com/calabash/calabash-android/blob/master/documentation/installation.md)。
#### 使用
1. 创建项目目录。
```bash
calabash-android gen
```
生成文件如下
```bash
features
|_support
| |_app_installation_hooks.rb
| |_app_life_cycle_hooks.rb
| |_env.rb
| |_hooks.rb
|_step_definitions
| |_calabash_steps.rb
|_my_first.feature(测试脚本)
```

2. 运行测试用例。
```bash
calabash-android run app-_test-debug.apk
```
运行结果
```bash
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
```
3. 关键字说明
> 
- Feature：主要是描述测试的功能。
- Scenario：场景，在这里可以简单的理解为一个个的细分用例，通常情况下需要多个场景拼接来完成一个具体的测试用例。
- Step：实现场景的步骤代码。
4. 生成测试报告
```bash
calabash-android run app-_test-debug.apk —format html —out reports.html —format pretty
```
![测试报告](/calabash/calabash_repost_shot.png)
5. 预定义自定义的Step
```ruby
Then /^I pass "([^\"]*)" scenario$/ do |text|
	if text == "login"
		wait_for_element_exists("* id:'btn_login'")
		tap_when_element_exists("* id:'btn_login'")
		wait_for_text("账号", timeout: 5)
		clear_text_in("android.widget.EditText index:0")
		clear_text_in("android.widget.EditText index:1")
		enter_text("android.widget.EditText index:0", "13241967851")
		enter_text("android.widget.EditText index:1", "cc123456")
		tap_when_element_exists("* id:'tv_login'")
		wait_for_text("校区名片", timeout: 20)
		screenshot_embed
		puts "登录结束"
	end
end
```
执行自定义的Step
```ruby
Feature: 登录测试
	Scenario: 使用正确手机号，密码登录
		Then I pass "login" scenario
		登录结束
```
#### 参考
> - [饿了么物流技术-Calabash探索](http://lrd.ele.me/2017/03/20/Calabash%E6%8E%A2%E7%B4%A21-Run%20Calabash/)
> - [Calabash-Android官方文档](https://github.com/calabash/calabash-android/blob/master/ruby-gem/lib/calabash-android/canned_steps.md)
> - [Calabash官方文档-Steps说明](https://github.com/calabash/calabash-android/blob/master/ruby-gem/lib/calabash-android/canned_steps.md)
