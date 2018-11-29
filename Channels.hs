module Channels where

import Control.Concurrent.Chan (newChan, writeChan, readChan)

main = do
  messages <- newChan
  writeChan messages "unbounded"
  writeChan messages "channels"

  -- Read a message from a channel, then output it.
  msg <- readChan messages
  putStrLn msg
  -- Do the same thing again, but more concisely.
  putStrLn =<< readChan messages
