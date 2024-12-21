java
readme
jacoco
log4j
lombok 
spotless

`Way of encoding file`

```text
[____] - length od huffamn sequence treated as a one character (4 bits)
[TREE] - encoded tree
[___]- number of additional zeros added to the end of the file (3 bits)
[MESSAGE] - encoded data
[ZEROES] - additional zeros (0-7 bits)
```