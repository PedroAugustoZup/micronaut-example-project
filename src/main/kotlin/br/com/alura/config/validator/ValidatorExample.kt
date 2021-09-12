package br.com.alura.config.validator

import jakarta.inject.Singleton
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

@MustBeDocumented
@Target(AnnotationTarget.FIELD, AnnotationTarget.CONSTRUCTOR)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [ValidatorExampleClass::class])
annotation class ValidatorExample(
    val message:String = "mensagem padrao"
)

@Singleton
class ValidatorExampleClass:ConstraintValidator<ValidatorExample, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return true
    }

}
