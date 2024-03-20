package com.qcbookapp.domain.model.author

import com.qcbookapp.domain.model.DomainException
import com.qcbookapp.domain.model.shared.CreatedAt
import com.qcbookapp.domain.model.shared.UpdatedAt
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import java.time.LocalDateTime

class AuthorTest : BehaviorSpec({
    Given("createメソッド") {
        And("正常系") {
            When("作成した時") {
                val name = "テスト 太郎"
                val kana = "てすと たろう"
                val created = Author.create(
                    name = AuthorName(name),
                    kana = AuthorKana(kana)
                )
                Then("著者IDが設定されていること") {
                    created.identifier.shouldNotBeNull()
                    created.identifier.value.shouldBeTypeOf<Long>()
                }
                Then("指定した著者名が設定されていること") {
                    created.name.value.shouldBe(name)
                }
                Then("指定した著者名(かな)が設定されていること") {
                    created.kana.value.shouldBe(kana)
                }
                Then("作成日時が設定されていること") {
                    assertSoftly(created.createdAt) {
                        shouldNotBeNull()
                        value.shouldBeTypeOf<LocalDateTime>()
                    }
                }
                Then("更新日時が設定されていること") {
                    assertSoftly(created.updatedAt) {
                        shouldNotBeNull()
                        value.shouldBeTypeOf<LocalDateTime>()
                    }
                }
            }
        }
        And("異常系") {
            And("著者名が空の場合") {
                val name = ""
                val kana = "てすと たろう"
                When("作成した時") {
                    Then("例外が発生すること") {
                        assertSoftly {
                            shouldThrow<DomainException> {
                                Author.create(
                                    name = AuthorName(name),
                                    kana = AuthorKana(kana)
                                )
                            }.message.shouldBe("著者名は1文字以上で入力してください")
                        }
                    }
                }
            }
            And("著者名(かな)が空の場合") {
                val name = "テスト 太郎"
                val kana = ""
                When("作成した時") {
                    Then("例外が発生すること") {
                        assertSoftly {
                            shouldThrow<DomainException> {
                                Author.create(
                                    name = AuthorName(name),
                                    kana = AuthorKana(kana)
                                )
                            }.message.shouldBe("著者名(かな)は1文字以上で入力してください")
                        }
                    }
                }
            }
            And("著者名が101文字の場合") {
                val name: String = "あ".repeat(101)
                val kana = "てすと たろう"
                When("作成した時") {
                    Then("例外が発生すること") {
                        assertSoftly {
                            shouldThrow<DomainException> {
                                Author.create(
                                    name = AuthorName(name),
                                    kana = AuthorKana(kana)
                                )
                            }.message.shouldBe("著者名は100文字以下で入力してください")
                        }
                    }
                }
            }
            And("著者名(かな)が101文字の場合") {
                val name = "テスト 太郎"
                val kana: String = "あ".repeat(101)
                When("作成した時") {
                    Then("例外が発生すること") {
                        assertSoftly {
                            shouldThrow<DomainException> {
                                Author.create(
                                    name = AuthorName(name),
                                    kana = AuthorKana(kana)
                                )
                            }.message.shouldBe("著者名(かな)は100文字以下で入力してください")
                        }
                    }
                }
            }
            And("著者名(かな)がひらがな以外の文字を含んでいる場合") {
                val name = "テスト 太郎"
                val kana = "てすと たろう1"
                When("作成した時") {
                    Then("例外が発生すること") {
                        assertSoftly {
                            shouldThrow<DomainException> {
                                Author.create(
                                    name = AuthorName(name),
                                    kana = AuthorKana(kana)
                                )
                            }.message.shouldBe("著者名(かな)はひらがなで入力してください")
                        }
                    }
                }
            }
        }
    }

    Given("updateメソッド") {

        // 更新前の状態
        val name = "テスト 太郎"
        val kana = "てすと たろう"
        val before: Author = Author.create(
            name = AuthorName(name),
            kana = AuthorKana(kana)
        )
        And("正常系") {
            When("更新した時") {
                val updatedName = "テスト 花子"
                val updatedKana = "てすと はなこ"
                val updated: Author = before.update(
                    name = AuthorName(updatedName),
                    kana = AuthorKana(updatedKana)
                )
                Then("著者IDが設定されていること") {
                    updated.identifier.shouldNotBeNull()
                    updated.identifier.value.shouldBeTypeOf<Long>()
                }
                Then("指定した著者名が設定されていること") {
                    updated.name.value.shouldBe(updatedName)
                }
                Then("指定した著者名(かな)が設定されていること") {
                    updated.kana.value.shouldBe(updatedKana)
                }
                Then("作成日時が設定されていること") {
                    assertSoftly(updated.createdAt) {
                        shouldNotBeNull()
                        value.shouldBeTypeOf<LocalDateTime>()
                    }
                }
                Then("更新日時が設定されていること") {
                    assertSoftly(updated.updatedAt) {
                        shouldNotBeNull()
                        value.shouldBeTypeOf<LocalDateTime>()
                    }
                }
            }
        }
        And("異常系") {
            And("著者名が空の場合") {
                val updatedName = ""
                val updatedKana = "てすと たろう"
                When("更新した時") {
                    Then("例外が発生すること") {
                        assertSoftly {
                            shouldThrow<DomainException> {
                                before.update(
                                    name = AuthorName(updatedName),
                                    kana = AuthorKana(updatedKana)
                                )
                            }.message.shouldBe("著者名は1文字以上で入力してください")
                        }
                    }
                }
            }
            And("著者名(かな)が空の場合") {
                val updatedName = "テスト 太郎"
                val updatedKana = ""
                When("更新した時") {
                    Then("例外が発生すること") {
                        assertSoftly {
                            shouldThrow<DomainException> {
                                before.update(
                                    name = AuthorName(updatedName),
                                    kana = AuthorKana(updatedKana)
                                )
                            }.message.shouldBe("著者名(かな)は1文字以上で入力してください")
                        }
                    }
                }
            }
            And("著者名が101文字の場合") {
                val updatedName: String = "あ".repeat(101)
                val updatedKana = "てすと たろう"
                When("更新した時") {
                    Then("例外が発生すること") {
                        assertSoftly {
                            shouldThrow<DomainException> {
                                before.update(
                                    name = AuthorName(updatedName),
                                    kana = AuthorKana(updatedKana)
                                )
                            }.message.shouldBe("著者名は100文字以下で入力してください")
                        }
                    }
                }
            }
            And("著者名(かな)が101文字の場合") {
                val updatedName = "テスト 太郎"
                val updatedKana: String = "あ".repeat(101)
                When("更新した時") {
                    Then("例外が発生すること") {
                        assertSoftly {
                            shouldThrow<DomainException> {
                                before.update(
                                    name = AuthorName(updatedName),
                                    kana = AuthorKana(updatedKana)
                                )
                            }.message.shouldBe("著者名(かな)は100文字以下で入力してください")
                        }
                    }
                }
            }
            And("著者名(かな)がひらがな以外の文字を含んでいる場合") {
                val updatedName = "テスト 太郎"
                val updatedKana = "てすと たろう1"
                When("更新した時") {
                    Then("例外が発生すること") {
                        assertSoftly {
                            shouldThrow<DomainException> {
                                before.update(
                                    name = AuthorName(updatedName),
                                    kana = AuthorKana(updatedKana)
                                )
                            }.message.shouldBe("著者名(かな)はひらがなで入力してください")
                        }
                    }
                }
            }
        }
    }
    Given("reconstructメソッド") {
        And("正常系") {
            When("再構築した時") {
                val id = AuthorId(1)
                val name = "テスト 太郎"
                val kana = "てすと たろう"
                val createdAt: CreatedAt = CreatedAt.of()
                val updatedAt: UpdatedAt = UpdatedAt.of()
                val reconstructed = Author.reconstruct(
                    id = id,
                    name = AuthorName(name),
                    kana = AuthorKana(kana),
                    createdAt = createdAt,
                    updatedAt = updatedAt
                )
                Then("指定した著者IDで再構築されていること") {
                    reconstructed.identifier.shouldBe(id)
                }
                Then("指定した著者名で再構築されていること") {
                    reconstructed.name.value.shouldBe(name)
                }
                Then("指定した著者名(かな)で再構築されていること") {
                    reconstructed.kana.value.shouldBe(kana)
                }
                Then("指定した作成日時が設定されていること") {
                    reconstructed.createdAt.shouldBe(createdAt)
                }
                Then("指定した更新日時が設定されていること") {
                    reconstructed.updatedAt.shouldBe(updatedAt)
                }
            }
        }
        And("異常系") {
            And("著者名が空の場合") {
                val id = AuthorId(1)
                val name = ""
                val kana = "てすと たろう"
                val createdAt: CreatedAt = CreatedAt.of()
                val updatedAt: UpdatedAt = UpdatedAt.of()
                When("再構築した時") {
                    Then("例外が発生すること") {
                        assertSoftly {
                            shouldThrow<DomainException> {
                                Author.reconstruct(
                                    id = id,
                                    name = AuthorName(name),
                                    kana = AuthorKana(kana),
                                    createdAt = createdAt,
                                    updatedAt = updatedAt
                                )
                            }.message.shouldBe("著者名は1文字以上で入力してください")
                        }
                    }
                }
            }
            And("著者名(かな)が空の場合") {
                val id = AuthorId(1)
                val name = "テスト 太郎"
                val kana = ""
                val createdAt: CreatedAt = CreatedAt.of()
                val updatedAt: UpdatedAt = UpdatedAt.of()
                When("再構築した時") {
                    Then("例外が発生すること") {
                        assertSoftly {
                            shouldThrow<DomainException> {
                                Author.reconstruct(
                                    id = id,
                                    name = AuthorName(name),
                                    kana = AuthorKana(kana),
                                    createdAt = createdAt,
                                    updatedAt = updatedAt
                                )
                            }.message.shouldBe("著者名(かな)は1文字以上で入力してください")
                        }
                    }
                }
            }
            And("著者名が101文字の場合") {
                val id = AuthorId(1)
                val name: String = "あ".repeat(101)
                val kana = "てすと たろう"
                val createdAt: CreatedAt = CreatedAt.of()
                val updatedAt: UpdatedAt = UpdatedAt.of()
                When("再構築した時") {
                    Then("例外が発生すること") {
                        assertSoftly {
                            val exception = shouldThrow<DomainException> {
                                Author.reconstruct(
                                    id = id,
                                    name = AuthorName(name),
                                    kana = AuthorKana(kana),
                                    createdAt = createdAt,
                                    updatedAt = updatedAt
                                )
                            }
                            exception.message.shouldBe("著者名は100文字以下で入力してください")
                        }
                    }
                }
            }
            And("著者名(かな)が101文字の場合") {
                val id = AuthorId(1)
                val name = "テスト 太郎"
                val kana: String = "あ".repeat(101)
                val createdAt: CreatedAt = CreatedAt.of()
                val updatedAt: UpdatedAt = UpdatedAt.of()
                When("再構築した時") {
                    Then("例外が発生すること") {
                        assertSoftly {
                            shouldThrow<DomainException> {
                                Author.reconstruct(
                                    id = id,
                                    name = AuthorName(name),
                                    kana = AuthorKana(kana),
                                    createdAt = createdAt,
                                    updatedAt = updatedAt
                                )
                            }.message.shouldBe("著者名(かな)は100文字以下で入力してください")
                        }
                    }
                }
            }
            And("著者名(かな)がひらがな以外の文字を含んでいる場合") {
                val id = AuthorId(1)
                val name = "テスト 太郎"
                val kana = "てすと たろう1"
                val createdAt: CreatedAt = CreatedAt.of()
                val updatedAt: UpdatedAt = UpdatedAt.of()
                When("再構築した時") {
                    Then("例外が発生すること") {
                        assertSoftly {
                            shouldThrow<DomainException> {
                                Author.reconstruct(
                                    id = id,
                                    name = AuthorName(name),
                                    kana = AuthorKana(kana),
                                    createdAt = createdAt,
                                    updatedAt = updatedAt
                                )
                            }.message.shouldBe("著者名(かな)はひらがなで入力してください")
                        }
                    }
                }
            }
        }
    }
})
