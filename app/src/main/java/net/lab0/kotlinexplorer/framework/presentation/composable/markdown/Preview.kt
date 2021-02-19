package net.lab0.kotlinexplorer.framework.presentation.composable.markdown

import org.commonmark.parser.Parser


fun parseMD(text: String) =
    Parser.builder().build().parse(text)

