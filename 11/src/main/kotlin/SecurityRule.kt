interface SecurityRule {

    fun validate(s: String): Boolean

}

class IOLMustBeAvoided : SecurityRule {
    override fun validate(s: String): Boolean {
        return !s.contains('i') && !s.contains('o') && !s.contains('l')
    }
}

class MustContainTwoPairs : SecurityRule {
    companion object {
        val regex = "[a-z]*([a-z])\\1[a-z]*([a-z])\\2[a-z]*".toRegex()
    }
    override fun validate(s: String): Boolean {
        return s.matches(regex)
    }
}

class MustContainThreeLettersIncrease : SecurityRule {
    companion object {
        val regex =
            ".+(abc|bcd|cde|def|efg|fgh|ghi|hij|ijk|jkl|klm|lmn|mno|nop|opq|pqr|qrs|rst|stu|tuv|uvw|vwx|wxy|xyz).+".toRegex()
    }

    override fun validate(s: String): Boolean {
       return s.matches(regex)
    }
}
