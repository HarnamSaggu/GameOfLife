fun main() {
	val width = 25
	val height = 15
	for (i in 1..10) {
		var board = generateRandomBoard(width, height)
		for (j in 1..10) {
			nextStep(board)
			printState(board)
			if (isEmpty(board)) board = generateRandomBoard(width, height)
			Thread.sleep(350)
		}
	}
}

fun nextStep(board: MutableList<MutableList<Boolean>>): MutableList<MutableList<Boolean>> {
	val nBoard = board.toMutableList()
	board.indices.forEach { y ->
		board[y].indices.forEach { x ->
			var neighbours = 0
			for (a in -1..1) {
				for (b in -1..1) {
					if (a == 0 && b == 0) continue
					val ny = y + a
					val nx = x + b
					if (ny !in 0 until board.size || nx !in 0 until board[0].size) continue
					if (board[y + a][x + b]) neighbours++
				}
			}
			nBoard[y][x] = false
			if (!board[y][x] && neighbours == 3) nBoard[y][x] = true
			if (board[y][x] && neighbours in 2..3) nBoard[y][x] = true
		}
	}
	return nBoard
}

fun isEmpty(board: MutableList<MutableList<Boolean>>): Boolean {
	board.forEach { column ->
		column.forEach { cell ->
			if (cell) return false
		}
	}
	return true
}

fun generateRandomBoard(width: Int, height: Int): MutableList<MutableList<Boolean>> {
	return MutableList(height) { MutableList(width) { (0..2).random() == 0 } }
}

fun printState(board: MutableList<MutableList<Boolean>>) {
	println("+${"-".repeat(board[0].size * 3)}+")
	board.forEach {
		print("|")
		it.forEach { x ->
			print(if (x) " # " else "   ")
		}
		println("|")
	}
	println("+${"-".repeat(board[0].size * 3)}+")
}
