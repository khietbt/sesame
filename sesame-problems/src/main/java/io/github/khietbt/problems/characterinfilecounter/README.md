# One Million Row Problem

Read / parse / process a very large file.

Reference: https://dev.to/jigarjm/high-throughput-file-reading-in-java-25oe

## Testing with a medium file

### Creating the file

dd if=/dev/urandom bs=100000 count=50 | base64 > mediumfile

mediumfile: 6754388 bytes

### Results

FileInputStream: 17818 ms
BufferedReader: 561 ms
FileChannelCounter & MappedByteBuffer: 97 ms

## Testing with a large file

### Creating the file

dd if=/dev/urandom bs=20000000 count=50 | base64 > largefile

### Results

FileChannelCounter: 5168 ms
MultiThreadFileChannelCounter:  1516 ms (8 threads)
VirtualMultiThreadFileChannelCounter:  1471 ms (8 threads)