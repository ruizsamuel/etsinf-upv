import heapq
import math
import time

# Used for states generation (getChildren())
dx = [-1, 1, 0, 0]
dy = [0, 0, 1, -1]

# Global variables holding algorithms

graphf_counter = 0
node_counter = 0
end_state ="123804765"
explored_counter = 0
heap_counter = 0
open_counter = 0
max_counter = 0
max_rev_counter = 0

graphf_path = []

graphf_cost = 0

graphf_depth = 0

time_graphf = 0.0

# function to get String representation
def getStringRepresentation(x):
    if int(math.log10(x)) + 1 == 9:
        return str(x)
    else:
        return "0" + str(x)


# function to generate all valid children of a certain node
def getChildren(state):
    children = []
    idx = state.index('0')
    i = int(idx / 3)
    j = int(idx % 3)
    for x in range(0, 4):
        nx = i + dx[x]
        ny = j + dy[x]
        nwIdx = int(nx * 3 + ny)
        if checkValid(nx, ny):
            listTemp = list(state)
            listTemp[idx], listTemp[nwIdx] = listTemp[nwIdx], listTemp[idx]
            children.append(''.join(listTemp))
    return children


# function to get the path to the goal state
def getPath(parentMap, inputState):
    path = []
    temp = 123804765
    while temp != inputState:
        path.append(temp)
        temp = parentMap[temp]
    path.append(inputState)
    path.reverse()
    return path


# function to print the path to goal
def printPath(path):
    for i in path:
        print(getStringRepresentation(i))


# function to check the goal state
def goalTest(state):
    if state == 123804765:
        return True
    return False


# function to check if the start state solvable or not
def isSolvable(digit):
    
    count = 0
    for i in range(0, 9):
        for j in range(i, 9):
            if digit[i] > digit[j] and digit[i] != '0':
                count += 1
        if digit[i] == '0' and i%2==0:
            count += 1

    return count % 2 == 0



# breadth first search algorithm

# function checking if state is valid or out of bounds
def checkValid(i, j):
    if i >= 3 or i < 0 or j >= 3 or j < 0:
        return 0
    return 1


# heuristic function using manhattan distance

def getManhattanDistance(state):
    tot = 0 
    for i in range(1,9):
        goal = end_state.index(str(i))
        goalX = int(goal/ 3)
        goalY = goal % 3
        idx = state.index(str(i))
        itemX = int(idx / 3)
        itemY = idx % 3
        tot += (abs(goalX - itemX) + abs(goalY - itemY))
    return tot

# heuristic function using manhattan distance

def getEuclideanDistance(state):
    tot = 0.0
    for i in range(1, 9):
        goal = end_state.index(str(i))
        goalX = int(goal / 3)
        goalY = goal % 3
        idx = state.index(str(i))
        itemX = int(idx / 3)
        itemY = idx % 3
        tot += math.sqrt(pow((goalX - itemX), 2) + pow((goalY - itemY), 2))
    return tot

def getPiezasDescolocadas(state):
    tot = 0
    for i in range(1, 9):
        goal = end_state.index(str(i))
        idx = state.index(str(i))
        if goal != idx: tot += 1
    return tot

def getSecuencias(state):
    tot = 0
    ant = 0
    for i in [1,2,5,8,7,6,3]:
        if str(state[i]) == '0':
            tot += 3
        elif int(state[ant]) + 1 == int(state[i]):
            tot += 0
        else:
            tot += 2
        ant = i
        
    tot = tot*3
    return tot

def getFilasColumnas(state):
    tot = 0
    for i in range(1, 9):
        goal = end_state.index(str(i))
        goalX = int(goal / 3)
        goalY = goal % 3
        idx = state.index(str(i))
        itemX = int(idx / 3)
        itemY = idx % 3
        if goalX != itemX: tot+= 1
        if goalY != itemY: tot+= 1
    return tot

def function_0(x):
    return 0;

def function_1(x):
    return 1

def function_N(x):
    return -1

def graphSearch(inputState,function_g,function_h,maximum_depth=-1):
    start_time = time.time()
    integer_state = int(inputState)
    heap = []
    explored = {}
    parent = {}
    cost_map = {}
    heapq.heappush(heap, (function_h(inputState), integer_state))
    
    cost_map[integer_state] = function_h(inputState)
    heap_map = {}
    heap_map[integer_state] = 1
    depth_map ={}
    depth_map[integer_state] = 0
    global graphf_counter
    global graphf_path
    global graphf_cost
    global graphf_depth
    global time_graphf
    global node_counter
    global explored_counter
    global heap_counter
    global max_counter
    global open_counter
    global max_rev_counter
    
    explored_counter = 0
    heap_counter = 0
    max_counter = 0
    graphf_depth = 0
    node_counter = 1
    open_counter = 0
    max_rev_counter = 0
    
    while heap:
        node = heapq.heappop(heap)
        state = node[1]
        string_state = getStringRepresentation(state)
        parent_cost = node[0] - function_h(string_state)
        #print("Parent cost: ", parent_cost)
        # handling the nodes that was renewed
        if not state in explored:
            graphf_depth = max(parent_cost, graphf_depth)
            explored_counter +=1
            open_counter -=1
        try:
            heap_map[state] -=1
            heap_counter -=1
            
        except:
            print("Error al explorar")
            
            
        explored[state] = 1
        
        if (explored_counter + heap_counter > max_counter):
            max_counter = explored_counter + heap_counter
            
        if (explored_counter + open_counter > max_rev_counter):
            max_rev_counter = explored_counter + open_counter
            
        if goalTest(state):
            path = getPath(parent, int(inputState))
            # printPath(path)
            graphf_path = path
            graphf_counter = (len(explored))
            graphf_cost = len(path) - 1
            time_graphf = float(time.time() - start_time)

            return 1
        # generating childeren
        
        if (maximum_depth==-1) or (depth_map[state]<maximum_depth):
        #if True:
            children = getChildren(string_state)
            for child in children:
                node_counter +=1
                new_cost = function_h(child)
                child_int = int(child)                 
                
                if (child_int not in heap_map):#Nodo completamente nuevo
                    heapq.heappush(heap, (parent_cost + new_cost + function_g(0), child_int))
                    heap_map[child_int] = 1
                    heap_counter +=1
                    open_counter +=1
                    cost_map[child_int] = parent_cost + new_cost + function_g(0)
                    parent[child_int] = state
                    depth_map[child_int]=depth_map[state]+1
                    graphf_depth = max(depth_map[child_int], graphf_depth)
                elif (child_int not in explored):
                    if abs(new_cost + parent_cost + function_g(0)) < abs(cost_map[child_int]):
                        parent[child_int] = state
                        cost_map[child_int] = new_cost + parent_cost + function_g(0)
                        heapq.heappush(heap, (parent_cost + function_g(0) + new_cost, child_int))
                        depth_map[child_int]=depth_map[state]+1
                        heap_map[child_int] += 1
                        heap_counter +=1
                        graphf_depth = max(depth_map[child_int], graphf_depth)
                        
                else:#esta cen cerrados
                    if abs(new_cost + parent_cost + function_g(0)) < abs(cost_map[child_int]):
                        parent[child_int] = state
                        cost_map[child_int] = new_cost + parent_cost + function_g(0)
                        heapq.heappush(heap, (parent_cost + function_g(0) + new_cost, child_int))
                        depth_map[child_int]=depth_map[state]+1
                        heap_map[child_int] = 1
                        heap_counter += 1
                        open_counter += 1
                        graphf_depth = max(depth_map[child_int], graphf_depth)
                        try:
                            del explored[child_int]
                            explored_counter -=1
                        except:
                            print('Borrado explored',child_int)
                        
                            
    graphf_cost = 0
    graphf_path = []
    graphf_counter = (len(explored))
    time_graphf = float(time.time() - start_time)
    
    return 0

def DFS_B(inputState,maximum_depth=-1,lim_ida=-1,function_h=None):
    start_time = time.time()
    explored = set()
    explored.add(inputState)

    global graphf_counter
    global graphf_path
    global graphf_cost
    global graphf_depth
    global time_graphf
    global node_counter
    
    #Nuevo
    global current_node_stored
    global max_node_stored
    global lim_sig
    path = []
    graphf_path = []
    graphf_counter = 0
    graphf_cost = 0
    graphf_depth = 0
    node_counter = 1
    
    #Nuevo
    current_node_stored = 1
    max_node_stored = 1
    
    # Para IDA*
    if lim_ida != -1:
        lim_sig = float('inf')
    
    def DFS_B_(string_state,depth,maximum_depth):
        global node_counter
        global graphf_depth
        global graphf_counter
                
        global current_node_stored
        global max_node_stored
        max_node_stored = max(current_node_stored, max_node_stored)

        #Para IDA
        global lim_sig

        # Cada vez que se llama a esta funcion recursiva es una expansion        
        graphf_counter += 1

        reached_goal = False
        integer_state = int(string_state)
        
        # Si llego al goal, devuelvo True
        if goalTest(integer_state):
            reached_goal = True
            graphf_depth = max(depth,graphf_depth)

        if lim_ida == -1:
            if depth == maximum_depth and not reached_goal:
                explored.remove(string_state)
                current_node_stored -= 1
                graphf_depth = maximum_depth
                return False
        
        #Para IDA*
        else:
            if depth + function_h(string_state) > lim_ida and not reached_goal:
                explored.remove(string_state)
                current_node_stored -= 1
                graphf_depth = depth
                
                lim_sig = min(lim_sig, depth + function_h(string_state))
                return False
        
        # Expandir si se puede y debe
        if not reached_goal:
            children = getChildren(string_state)
            node_counter += len(children)
            
            # Nuevo
            current_node_stored += len([i for i in children if i not in explored])
            
            for child in children:
                if child not in explored:
                    explored.add(child)
                    #Recursion
                    reached_goal = DFS_B_(child,depth+1,maximum_depth)
                    #Si llego al objetivo, lo devuelvo
                    if reached_goal == True:
                        path.append(int(child))
                        return reached_goal
                
    
        # Aquí solo llega si ningún hijo ha devuelto una solución
        if reached_goal == False:
            explored.remove(string_state)
            current_node_stored -= 1
        return reached_goal
    
    reached_goal = DFS_B_(inputState,0,maximum_depth)


    if reached_goal:    
        path.append(int(inputState))
        graphf_path = list(reversed(path))
        
        graphf_cost = len(path) - 1
        time_graphf = float(time.time() - start_time)
        
        if lim_ida != -1:
            return (1,lim_sig)
            
        return 1
    
    if lim_ida != -1:
        return (0,lim_sig)
    
    return 0


def ID_B(inputState):
    start_time_id = time.time()
    global graphf_counter
    global graphf_path
    global graphf_cost
    global graphf_depth
    global time_graphf
    global node_counter
    
    global max_node_stored
    
    path = []
    counter = 0
    cost = 0
    depth = 0
    node = 0
    
    #Nuevo
    max_node = 1

    sol_reached = 0
    max_depth = 1
    
    while sol_reached == 0:
                
        sol_reached = DFS_B(inputState,max_depth)
        
        path = graphf_path
        counter += graphf_counter
        cost = graphf_cost
        depth = graphf_depth
        node += node_counter
        max_node = max_node_stored
        
        if sol_reached == 1:
            print(f'ID: Sol reached at depth {max_depth}')
            
        max_depth += 1
        
    graphf_path = path
    graphf_counter = counter
    graphf_cost = cost
    graphf_depth = depth
    node_counter = node
    max_node_stored = max_node
    time_graphf = float(time.time() - start_time_id)

    return 1    

def IDA_B(inputState,function_h):
    start_time_id = time.time()
    global graphf_counter
    global graphf_path
    global graphf_cost
    global graphf_depth
    global time_graphf
    global node_counter
    
    #Nuevo
    global max_node_stored
    
    path = []
    counter = 0
    cost = 0
    depth = 0
    node = 0
    
    #Nuevo
    max_node = 1

    sol_reached = 0
    
    # Limite inicial
    lim = function_h(inputState)
    
    while sol_reached == 0:
                
        sol_reached,lim_sig = DFS_B(inputState,-1,lim,function_h)
        
        path = graphf_path
        counter += graphf_counter
        cost = graphf_cost
        depth = graphf_depth
        node += node_counter
        max_node = max_node_stored
                
        if lim_sig == float('inf'):
            return 0

        lim = lim_sig
        
    graphf_path = path
    graphf_counter = counter
    graphf_cost = cost
    graphf_depth = depth
    node_counter = node
    max_node_stored = max_node
    time_graphf = float(time.time() - start_time_id)

    return 1    

