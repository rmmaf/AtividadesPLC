module MVars where

import Control.Concurrent

sleepMs n = threadDelay (n * 1000)

main = do
    result <- newEmptyMVar

    forkIO (do
        sleepMs 5
        putStrLn "Calculated result!"
        putMVar result 42)

    putStrLn "Waiting..."
    value <- takeMVar result
    putStrLn ("The answer is: " ++ show value)
