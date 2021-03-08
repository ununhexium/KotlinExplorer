package net.lab0.kotlinexplorer.business.course.data.kotlin.helloworld

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.LessonPage.InfoPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object Indentation : LessonImpl(
  id = "kotlin.helloworld.indentation",
  title = "Indentation",
  pages = listOf(
    LessonPage.CodeQuestionPage(
      title = "Indent",
      question = """
Indent the code by 4 spaces.
""",
      snippet = """
fun main() {  // start of block
${p(0)}print("Hello")
}
""",
      explanation = """
When writing code inside a block,
the convention is to indent the code by some amount of spaces.
It doesn't change the behaviour of the program, but makes it easier to read.
""",
      answer = listOf("    "),
      confusion = listOf("→→→→"),
    ),
    LessonPage.CodeQuestionPage(
      title = "Indent more",
      question = """
Indent the code by the right amount of spaces.
""",
      snippet = """
fun main() {  // start of block
${p(0)}print(  // start of continuation
${p(1)}"Hello"
${p(2)})  // end of continuation
}  // end of block
""",
      explanation = """
The deeper the block, the more indented it is.

You can also notice that the second block is indented 
once for the first block and twice for the second block,
 for a total of 12 spaces. This is a continuation indent.
 
Continuation indent are used when thing are normally on a 
single line but they get long and don't fit anymore.
This continuation can be made longer to differentiate it 
from the regular block indent.
""",
      answer = listOf("    ", "            ", "    "),
      confusion = listOf("→      →", "                "),
    ),
    InfoPage(
      title = "Summary",
      markdown = """
Indentation is for human readers.

For each opening block, the indentation increases by 4 additional spaces.

The indentation decreases at the end of a block.

4 spaces is the default, it can be any amount.
In the code example here we will use only 2 spaces because the code is about 40 characters wide.

Don't worry about when to use continuation of regular indent, 
your future code editor will do it automatically for you.
You just need to know that this will often happen.
"""
    ),
  )
)
