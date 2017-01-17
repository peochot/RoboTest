*** Settings ***
Documentation     A submission test suite
...
...               Some comment
Resource          resource.robot
Suite Setup        Open Site
Suite Teardown     Close Browser
*** Test Cases ***
Search And Submit
    Spinner should be visible
    Wait for spinner disappear
    Enter Query
    Click Search
    Spinner should be visible
    Wait for spinner disappear
    Submit button should be disabled
    Select Random Score  left
    Select Random Score  right
    Submit button should be enabled
    #Click submit feedback
    #Feedback spinner should be visible
    #Wait for feedback submission
    Click on feedback tab
    Log to console  ${keyword}
*** Keywords ***
Feedback spinner should be visible
    Element Should Be Visible  css = img.feedback__spinner

Wait for feedback submission
    Wait Until Element Is Not Visible  css = img.feedback__spinner

Click on feedback tab
    Click Element  css =.menu__item:nth-child(2)
