# Experimentation and Evaluation
## Measuring execution time of sorting algorithms

In this experiment, three sorting algorithms from the company Bubble Inc. are tested in order to find the fastest one.

This repository contains the code, data and report for the Experiment.

To be able to replicate the experiment, please read the PDF paper carefully, especially Sections 3 and 4. 
All materials and software used are referenced in the Appendix A.

## Replicating the experiment

The experiment can be replicated by following these steps:

1. Clone the repository
2. Install the required software
3. Run the experiment (see below)
4. Generate the plots (see below)

### Running the experiment

First, you need to compile all the java files. To do so navigate to the [`Assignment1`](`Assignment1`) folder and run the following command:

```bash
javac *.java
```

Then, you can run the experiment by running the following command:

```bash
java BubbleSortComparator output_file.csv
```

If the output file already exists, it will asked if you want to overwrite it.
The output file will contain the execution time of each sorting algorithm for each array size and each data-type.

Note that the experiment will take a long time to run. It is recommended to run it overnight.

For more information about the java code, please read the javadoc report: [`Assignment1/javadoc/index.html`](Assignment1/javadoc/index.html).

### Generating the plots
Once the experiment has been run, you can generate the plots by navigating to the [`Assignment1/plotting`](`Assignment1/plotting`) directory and running the following command:

```bash
python plot.py input_file.csv output_file.csv output/directory/
```

The input file is the output file generated by the experiment. The output file is the name of the output file that will be generated. The output directory is the directory where the plots will be saved.
If the output file already exists, it will asked if you want to overwrite it.