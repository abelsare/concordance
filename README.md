# concordance
Poetic Concordance system that shows you the relevant text from Shakespeare sonnets based on the input word.
For eg: Seraching for the word "foe" will return the following result from the Sonnet

Sonnet 1:

Thyself thy foe, to thy sweet self too cruel.
Thou that art now the world's fresh ornament

It will list all the Sonnets where the search term is found. The results show the line in which the word is found and the line below it.

To run:
Compile using mvn clean install
Run the jar using java -jar target/target/concordance-1.0-SNAPSHOT.jar <input-directory>
