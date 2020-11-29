package com.example.jetpackcomposeexplorer.model

val homePage = PageID("homePage")
val notFoundPage = PageID("notFoundPage")

// tutorial
val tutorialHomePage = PageID("tutorial")
val tutorialIntroductionPage = PageID("tutorial/introduction", tutorialHomePage)
val tutorialPage1 = PageID("tutorial/page1", tutorialHomePage)
val tutorialPage2 = PageID("tutorial/page2", tutorialHomePage)
val tutorialWrongNamePage = PageID("tutorial/wrongName", tutorialHomePage)
val tutorialCorrectNamePage = PageID("tutorial/correctName", tutorialHomePage)

