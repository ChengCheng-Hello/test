require 'calabash-android/calabash_steps'
# Given /^I press the "([^\"]*)" text$/ do |text|
#   tap_when_element_exists("android.widget.TextView {text CONTAINS[c] '#{text}'}")
# end

# Then /^I press text number (\d+)$/ do |index|
#   tap_when_element_exists("android.widget.TextView index:#{index.to_i-1}")
# end
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