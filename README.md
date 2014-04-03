ML-Toolbox
==========
Trying out different Machine Learning techniques to draw a painting.

Input is an image file.

For example, draw a painting using only circles. Each new "hypothesis", or recipe to draw a picture is constructed by a random set of circles. These hypotheses are then compared with each other to select the better one. The best one will continue to the next round. For each round, a new random set of hypotheses is created using the current best one with small alterations. Eventually the best hypothesis will get closer and closer to the real image.
