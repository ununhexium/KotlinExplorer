package net.lab0.kotlinexplorer

import org.junit.jupiter.api.Test
import java.io.File

// https://kotlinlang.org/docs/basic-syntax.html#creating-classes-and-instances
// https://developer.android.com/kotlin/learn
@Suppress("UNUSED_VARIABLE")
class Courses {
  interface Dep {
    val name: String
  }

  data class Course(
    override val name: String,
    val description: String,
    val dependencies: List<Dep> = listOf()
  ) : Dep

  data class Project(
    override val name: String,
    val dependencies: List<Dep> = listOf()
  ) : Dep

  val courses = mutableListOf<Course>()
  val groups = mutableListOf<Project>()

  fun course(name: String, vararg dependencies: Dep) =
    Course(name, "", dependencies.toList()).also { courses.add(it) }

  fun course(name: String, description: String, vararg dependencies: Dep) =
    Course(name, description, dependencies.toList()).also { courses.add(it) }

  fun project(name: String, vararg dependencies: Dep) =
    Project(name, dependencies.toList()).also { groups.add(it) }

  @Test
  fun `list kotlin courses`() {
    // Hello world
    val strings = course("String")
    val print = course("Print", "print, output, stdout, show")
    val main = course("Main", "main, start, entry point")

    val helloWorld = project(
      "Hello World",
      print,
      main,
      strings,
    )

    val value = course("Value", "val, value, constant")
    val integers = course("Integers")

    // TO DO: string lexicographical comparison: "a" < "b"
    val comparison = course("Comparison of integers", "greater than, less than, equal")
    val ifElse = course("If else", "if, else")
    val stringConcatenation = course("String concatenation", strings)

    val positivenegative = project(
      "Positive or Negative",
      helloWorld,
      ifElse,
      value,
      comparison,
      stringConcatenation,
    )


    // how to import and use an object
    val random = course("Random basics", "random")
    val importKw = course("Import")

    val variable = course("Variables", "var, variable, assignment")
    val whileLoop = course("While", ifElse)

    val plusOrMinus = project(
      "Basics",
      helloWorld,
      value,
      variable,
      ifElse,
      whileLoop,
    )

    val nullable = course("Null", "null, nullable, ?", value, variable)
    val functionCalls = course("Function")
    val comment = course("Comments", "comment, //, /**/")
    val nestedBlockComments = course("Nested comments", "comment, //, /*/**/*/")

    // data types
    val boolean = course("Boolean", plusOrMinus)
    val floatingPointNotation = course("Floating point notation")
    val floatingPoint = course("Floats", plusOrMinus, floatingPointNotation)
    val floatingPointBehaviour = course("Floating point behaviour", floatingPoint)
    val characters = course("Characters", plusOrMinus)

    val dataTypes = project(
      "Data types",
      integers,
      boolean,
      floatingPoint,
      characters,
      strings,
    )

    val stringTemplates = course("String templates", "dollar, $, \${}, string template", strings)

    val deferredAssignment = course("Deferred assignment", "deferred, assign later", dataTypes)
    val typeInference = course("Type inference for variables")

    // operators
    val unaryPlus = course("Unary plus", integers)
    val unaryMinus = course("Unary minus", integers)
    val operatorPriorities = course("Operator priorities", integers)

    val operators = project(
      "Operators",
      unaryPlus
    )

    val operatorsOverloading = course("Operators overload")

    val typeInferenceForFunctions = course("Type inference for functions", value, variable)
    val topLevelDeclaration = course("Top level declaration", "top level, declaration", dataTypes)

    // functions
    val functionParameters = course("Function", integers, functionCalls)
    val returnKw = course("Return", "return, result", integers, functionCalls)
    val unit = course("Unit", "unit, void, no return", returnKw)
    val functionEqual = course("fun foo() = notation", functionCalls, integers)
    val closures = course("Closures", functionCalls, value, typeInferenceForFunctions)

    val mainArgs = course("Main cli args", "main, arguments, command line, inputs", main)

    // rare keywords
    val constKw = course("Constant", "const, compile-time", value)

    // special characters
    val escapeSequences = course("Escape sequence", comment)


    val switchCase = course("When", ifElse)

    // expressions
    val expressions = course("Expression assignment", "expressions", ifElse)

    // loops
    val doWhileLoop = course("Do while", ifElse)
    val continues = course("Continue", whileLoop, ifElse)

    // Math
    val mathLib = course("Math", "min, max, square root", functionCalls)

    // collections
    val list = course("List")
    val array = course("Array")

    // ranges
    val intRange = course("Integer range")
    val inRange = course("x in a..b")
    val outRange = course("x !in a..b")

    // structure
    val packageKw = course("Package")

    val classes = course("Classes", "class", functionCalls)

    val defaultConstructor = course("Default constructor", classes)
    val fields = course("Class fields (in constructor)", classes)
    val properties = course("Class properties", classes)

    val inheritance = course("Open class", "inheritance, open, derive", classes)

    val abstractClasses = course("Abstract classes", classes)
    val interfaces = course("Interface", classes, abstractClasses)

    val asOperator = course("As operator", classes)
    val isOperator = course("Is operator", classes)

    val delegatePattern = course("Delegate pattern", "by, delegate", classes, interfaces)
    val delegatedProperties = course("As operator", classes)

    // then build tree

    // then flatten

    // then output .dot file
    File("/home/uuh/dev/ununhexium/JetpackComposeExplorer/topics.dot").writeText(
      "digraph g { \n" +
          courses.joinToString(separator = "\n", prefix = "\n", postfix = "\n") {
            it.name.quoted()
          } +

          courses.joinToString(separator = "\n", prefix = "\n", postfix = "\n") { course ->
            course.dependencies.joinToString("\n") {
              course.name.quoted() + " -> " + it.name.quoted()
            }
          } + "}"
    )
  }

  private fun String.quoted() =
    '"' + this + '"'
}
