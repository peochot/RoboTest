*** Settings ***
Documentation     A search test suite
...
...               Some comment
Resource          resource.robot
Suite Setup        Open Site
Suite Teardown     Close Browser
*** Test Cases ***
Empty Query Search
    Spinner should be visible
    Wait for spinner disappear
    Print search result
    Click Search
    Spinner should be visible
    Wait for spinner disappear
    Submit button should be disabled
    Select Random Score  left
    Select Random Score  right
    Submit button should be disabled
    Print search result
Simple Query Search
    Enter Query
    Click Search
    Spinner should be visible
    Wait for spinner disappear
    Submit button should be enabled
    Select Random Score  left
    Select Random Score  right
    Submit button should be enabled
    Print search result
Search Query And Scroll Down
    Enter Query
    Click Search
    Spinner should be visible
    Wait for spinner disappear
    Print search result
    Scroll down to continue search
    Spinner should be visible
    Wait for spinner disappear
    Scroll down to continue search
    Spinner should be visible
Search Query And Gender
    Enter Query
    Click Search
    Spinner should be visible
    Wait for spinner disappear
    Select Random Gender
    Spinner should be visible
    Wait for spinner disappear
    Submit button should be enabled
