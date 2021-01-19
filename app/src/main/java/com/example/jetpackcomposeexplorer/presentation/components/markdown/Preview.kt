package com.example.jetpackcomposeexplorer.presentation.components.markdown

import org.commonmark.parser.Parser


fun parseMD(text: String) =
    Parser.builder().build().parse(text)

