"""
Tic Tac Toe Player
"""

import math
import copy

X = "X"
O = "O"
EMPTY = None


def initial_state():
    """
    Returns starting state of the board.
    """
    return [[EMPTY, EMPTY, EMPTY],
            [EMPTY, EMPTY, EMPTY],
            [EMPTY, EMPTY, EMPTY]]


def player(board):
    """
    Returns player who has the next turn on a board.
    """
    count = 0
    for fila in board:
        for elemento in fila:
            if elemento is not EMPTY:
                count += 1

    if count & 1 == 0:
        return X  # Es par
    else:
        return O  # Es impar


def actions(board):
    """
    Returns set of all possible actions (i, j) available on the board.
    """
    conjunto = set()
    for fila_id, fila in enumerate(board):
        for celda_id, celda in enumerate(fila):
            if celda is EMPTY:  
                conjunto.add((fila_id, celda_id))
    return conjunto
    

def result(board, action):
    """
    Returns the board that results from making move (i, j) on the board.
    """
    copy_board = copy.deepcopy(board)
    setactions = actions(board)
    print(action)
    print(setactions)
    if action not in setactions:
     raise RuntimeError ("The action is not a valid action for the board")
    else:
         i, j = action
         copy_board[i][j]=player(board)
    return copy_board
    


def winner(board):
    """
    Returns the winner of the game, if there is one.
    """
    x_wins = False
    o_wins = False

   # Wins vertically (rows)
    for fila in board:
        if fila.count(X) == 3:
            x_wins = True
        elif fila.count(O) == 3:
            o_wins = True

    # Wins horizontally (columns)
    for i in range(3):
        columna = [board[j][i] for j in range(3)]
        if columna.count(X) == 3:
            x_wins = True
        elif columna.count(O) == 3:
            o_wins = True

    # Wins diagonally
    diagonal1 = [board[i][i] for i in range(3)]
    diagonal2 = [board[i][2 - i] for i in range(3)]
    if diagonal1.count(X) == 3 or diagonal2.count(X) == 3:
        x_wins = True
    elif diagonal1.count(O) == 3 or diagonal2.count(O) == 3:
        o_wins = True

    if x_wins and o_wins:
        raise ValueError("Board no valid!")

    if x_wins:
        return X
    elif o_wins:
        return O
    return None


def terminal(board):
    """
    Returns True if game is over, False otherwise.
    """
    if winner(board) is not None: # if X or O won, also to validate the board
        return True
    
    for fila in board: # if there is still a playable cell
        if EMPTY in fila:
            return False
    
    return True # there was a tie and the cells ran out


def utility(board):
    """
    Returns 1 if X has won the game, -1 if O has won, 0 otherwise.
    I guess board will always be a finished board i.e. terminal(board) is true
    """
    if winner(board) is X: return 1
    if winner(board) is O: return -1
    return 0


def minimax(board):
    """
    Returns the optimal action for the current player on the board.
    """
    # I have decided to perform minimax Alpha-beta pruning

    player_turn = player(board)
    if terminal(board):
        return None

    if player_turn == X:
        value, action = max_value(board, float("-inf"), float("inf"))
    else:
        value, action = min_value(board, float("-inf"), float("inf"))

    return action

def max_value(board, alpha, beta):
    if terminal(board):
        return utility(board), None

    max_eval = float("-inf")
    best_action = None

    for action in actions(board):
        new_board = result(board, action)
        eval, _ = min_value(new_board, alpha, beta)

        if eval > max_eval:
            max_eval = eval
            best_action = action

        alpha = max(alpha, max_eval)

        if beta <= alpha:
            break

    return max_eval, best_action

def min_value(board, alpha, beta):
    if terminal(board):
        return utility(board), None

    min_eval = float("inf")
    best_action = None

    for action in actions(board):
        new_board = result(board, action)
        eval, _ = max_value(new_board, alpha, beta)

        if eval < min_eval:
            min_eval = eval
            best_action = action

        beta = min(beta, min_eval)

        if beta <= alpha:
            break

    return min_eval, best_action