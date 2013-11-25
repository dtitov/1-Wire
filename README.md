1-Wire
======

The search algorithm is a binary tree search where branches are followed until a device ROM number, or leaf, is found. Subsequent searches then take the other branch paths until all of the leaves present are discovered.
The search algorithm begins with the devices on the 1-Wire being reset using the reset and presence pulse sequence. If this is successful then the 1-byte search command is sent. The search command readies the 1-Wire devices to begin the search.

http://www.maximintegrated.com/app-notes/index.mvp/id/162
