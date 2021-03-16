package net.lab0.grammar.kotlin

sealed class KotlinHighlight {
  object ANNOTATION : KotlinHighlight()
  object BACKGROUND : KotlinHighlight()
  object BRACKET : KotlinHighlight()
  object CLASS_DECLARATION : KotlinHighlight()
  object CHARACTER : KotlinHighlight()
  object CHARACTER_ESCAPED : KotlinHighlight()
  object TYPE : KotlinHighlight()
  object MODIFIER : KotlinHighlight()
  object CLASS_PARAMETER : KotlinHighlight()
  object COMMA : KotlinHighlight()
  object COMMENT : KotlinHighlight()
  object DEFAULT_TEXT : KotlinHighlight()
  object FUNCTION : KotlinHighlight()
  object INSTANCE_PROPERTY : KotlinHighlight()
  object KEYWORD : KotlinHighlight()
  object NUMBER : KotlinHighlight()

  sealed class OPERATOR : KotlinHighlight() {
    object ARITHMETIC_OPERATOR : OPERATOR()
    object ASSIGNMENT_OPERATOR : OPERATOR()
    object COMPARISON_OPERATOR : OPERATOR()
    object PREFIX_OPERATOR : OPERATOR()
    object POSTFIX_OPERATOR : OPERATOR()
  }

  object SIMPLE_IDENTIFIER : KotlinHighlight()
  object STRING : KotlinHighlight()
  object STRING_ESCAPED_CHARACTER : KotlinHighlight()
}
