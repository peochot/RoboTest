*** Settings ***
Library          Selenium2Library
Library          Utils.py
Library          String
Library          XvfbRobot
*** Variables ***
${SiteUrl}        http://localhost:8000/
${DELAY}          5
${keyword}
*** Keywords ***
Open Site
    Start Virtual Display    1920    1080
    Open Browser   ${SiteUrl} 
    Set Window Size    1920    1080
Enter Query
    ${keyword} =  Get Valid Keyword
    Input Text  css = input#searchContent  ${keyword}

Spinner should be visible
    Element Should Be Visible  css = img.search-result__spinner

Print search result
    ${result} =  Get Text  css = .search-result__title--left
    Log to console  ${result}

Wait for spinner disappear
    Wait Until Element Is Not Visible  css=img.search-result__spinner

Click Search
    Click Element  css = input#searchButtonTopSubmit

Select Random Score
    [Arguments]  ${position}
    ${score} =  Get Random Score
    Click Element  css = .feedback__${position} .feedback__score>button:nth-child(${score})

Submit button should be disabled
    Element Should Be Disabled  css = button.feedback__submit

Submit button should be enabled
    Element Should Be Enabled  css = button.feedback__submit

Click submit feedback
    Click Element  css = button.feedback__submit
Select Random Gender
    ${tab_number} =  Get Gender Tab
    Click Element  css = li.tabs__tab:nth-child(${tab_number})

Scroll down to continue search
    Execute JavaScript    window.scrollTo(0, 0)
    Execute JavaScript    window.scrollTo(0, document.body.scrollHeight+500)
