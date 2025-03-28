/*
 * Copyright 2023 dragonfly.ai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import narr.*
import slash.vector.*
import slash.matrix.*

class MatTest extends munit.FunSuite {

  val m0:Mat[3, 3] = Mat[3,3](
    1, 2, 3,
    4, 5, 6,
    7, 8, 9
  )

  //println(m0)

  val m:Mat[11, 7] = Mat.random[11, 7]
  val mT: Mat[7, 11] = m.transpose

  test( " m == m.transpose.transpose " ) {
    assertMatrixEquals(m0, m0.transpose.transpose)
    assertMatrixEquals(m, mT.transpose)
  }

  test(" compare m.rowVector to m.transpose.columnVector ") {

    // compare m's row vectors to mT's column vectors:
    var i: Int = 0
    while (i < m.rowDimension ) {
      assertVecEquals(m.rowVector(i), mT.columnVector(i))
      i += 1
    }

    var j: Int = 0
    while (j < m.columns) {
      assertVecEquals(m.columnVector(j), mT.rowVector(j))
      j += 1
    }
  }
}
