module Main where 

import Control.Concurrent
import Control.Concurrent.MVar
import Control.Concurrent.STM

waitThreads fim = 
  do f <- takeMVar fim
     if (f > 0) then
         do putMVar fim f
            waitThreads fim
     else 
         return ()

fornecedor :: TVar Int -> TVar Int -> TVar Int -> MVar Int -> IO()
fornecedor pao carne tomate fim = do
--                                   f <- takeMVar fim
                                   atomically (do
                                                writeTVar pao 30
                                                writeTVar carne 30
                                                writeTVar tomate 30
                                              )
---                                   putMVar fim (f-1)
                                   fornecedor pao carne tomate fim

produtor :: TVar Int -> TVar Int -> TVar Int -> MVar Bool ->   TVar Int -> MVar Int -> IO()
produtor pao carne tomate faca  sand fim = do
                  f <- takeMVar faca
--                  fi <- takeMVar fim
                  atomically (do 
                               p <- readTVar pao
                               c <- readTVar carne
                               t <- readTVar tomate
                               if (p <= 0 || c <= 0 || t <= 0) 
                               then retry
                               else
                                do 
                                    s <- readTVar sand
                                    writeTVar pao (p-1)
                                    writeTVar carne (c-1)
                                    writeTVar tomate (t-1)
                                    writeTVar sand (s+1)
                                   
                        )
                  putMVar faca f
--                  putMVar fim (fi -1)
                  produtor pao carne tomate faca sand fim
main = do
        pao <- atomically (newTVar 30)
        carne <- atomically (newTVar 30)
        tomate <- atomically (newTVar 30)
        sand <- atomically (newTVar 0 )
        faca <- newMVar False 
        fim <- newMVar 10 
        forkIO(fornecedor pao carne tomate fim)
        forkIO(produtor pao carne tomate faca sand fim)
        forkIO(produtor pao carne tomate faca sand fim)
        threadDelay (10000)
        --waitThreads fim
        p <- atomically (readTVar pao)
        c <- atomically (readTVar carne)
        t <- atomically (readTVar tomate)
        s <- atomically (readTVar sand)
        putStrLn ("Pao: " ++ show p ++ " Carne: "  ++ show c ++ " Tomate: " ++ show t ++ " Sanduiche: " ++ show s )         
        
        putStrLn "Terminou" 