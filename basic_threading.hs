import Control.Concurrent
import Data.Foldable (for_)

printMessagesFrom name = for_ [1..3] printMessage
  where printMessage i = do
          sleepMs 1
          putStrLn (name ++ " number " ++ show i)

sleepMs n = threadDelay (n * 1000)

main = do
  printMessagesFrom "main"
  forkIO $ printMessagesFrom "fork"
  forkIO $ do
    putStrLn "starting!"
    sleepMs 5
    putStrLn "ending!"
