import Data.List
--questao 1
s1 = (+2)
s2 = (>3)
--questao 2
--letra a
data CInt = Conjunto [Int] deriving (Show)
makeSetList :: [Int] -> [Int]
makeSetList [] = []
makeSetList l
    |elem (head l) (tail l) = makeSetList (tail l)
    |otherwise = [head l] ++ makeSetList (tail l)
makeSet :: [Int] -> CInt
makeSet l = Conjunto (makeSetList (Data.List.sort l))
--letra b
unionSet :: CInt -> CInt -> CInt
unionSet (Conjunto l1) (Conjunto l2)= makeSet(l1 ++ l2)
--letra c
mapSet :: (Int -> Int) -> CInt -> CInt
mapSet s1 (Conjunto l) = makeSet(map (s1) l)
--questao 3
type Texto = String
type Id = String
type DataHoraPub = Int
data Post = Post (Id, DataHoraPub) Texto deriving (Show, Eq)
data Thread = Nil | T Post (Thread)
--letra a
instance Show Thread where
show Nil = ""
show (T (Post(id, datah) text) fred) = "(" ++ id ++ " " ++ (Prelude.show datah) ++ " " ++ text ++ ")" ++ (Main.show fred)
--letra b
inserirPost :: Post -> Thread -> Thread
inserirPost post fred = T post fred
--letra c
threadToList :: Thread -> [Post]
threadToList Nil = []
threadToList (T post fred) = [post] ++ threadToList fred 
--letra d
listToThread :: [Post] -> Thread
listToThread [] = Nil
listToThread posts = T (head posts) (listToThread (tail posts))
--letra e
removerPost:: (Id, DataHoraPub) -> Thread -> Thread
removerPost (id, datah) fred = listToThread(filter (\(Post (id1, datah1) text) -> (id /= id1) && (datah /= datah1)) (threadToList fred) ) 