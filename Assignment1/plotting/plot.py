import sys
import os
import pandas as pd
import matplotlib.pyplot as plt

# get the path to this file
path = os.path.dirname(os.path.realpath(__file__))

# read the path from the arguments or use the default path
PATH = sys.argv[1] if len(sys.argv) > 1 else 'Assignment1/results.csv'
OUT_CSV = sys.argv[2] if len(sys.argv) > 2 else f'{path}/output.csv'
PATH_TO_PLOTS = sys.argv[3] if len(sys.argv) > 3 else f'{path}/plots/'


# Usefull functions to print and save the table results
def print_table_header():
    header = ['Ordering', 'Type', 'Algorithm', 'Min', 'Max', 'First Quartile', 'Median', 'Third Quartile']
    print('┌' + '─' * 15 + '┬' +'─' * 18 + '┬' + '─' * 24 + ('┬' + '─' * 15)*5 + '┐')
    print('│{:15}│{:18}│{:24}│{:15}│{:15}│{:15}│{:15}│{:15}│'.format(*header))
    print('├' + '─' * 15 + '┼' +'─' * 18 + '┼' + '─' * 24 + ('┼' + '─' * 15)*5 + '┤')

def print_table_footer():
    print('└' + '─' * 15 + '┴' +'─' * 18 + '┴' + '─' * 24 + ('┴' + '─' * 15)*5 + '┘')

def print_table_row(ordering, data_type, algo_type, min, max, first_quartile, median, third_quartile):
    print('│{:15}│{:18}│{:24}│{:15.2e}│{:15.2e}│{:15.2e}│{:15.2e}│{:15.2e}│'.format(ordering, data_type, algo_type, min, max, first_quartile, median, third_quartile))

def init_csv_output():
    # check if the file exists
    if(os.path.exists(OUT_CSV)):
        # if it exists, ask the user if he wants to overwrite it in red
        print('\033[91m' + 'The file {} already exists. Do you want to overwrite it? (y/n)'.format(OUT_CSV) + '\033[0m')
        if(input() != 'y' and input() != 'Y'):
            # if the user doesn't want to overwrite the file, exit
            print('Exiting...')
            exit()
        else:
            # if the user wants to overwrite the file, delete it
            os.remove(OUT_CSV)
    
    # create the file and write the header
    with open(OUT_CSV, 'w') as f:
        f.write('Ordering,Type,Algorithm,Min,Max,First Quartile,Median,Third Quartile\n')

def save_csv_row(ordering, data_type, algo_type, min, max, first_quartile, median, third_quartile):
    with open(OUT_CSV, 'a') as f:
        f.write('{},{},{},{},{},{},{},{}\n'.format(ordering, data_type, algo_type, min, max, first_quartile, median, third_quartile))



results = pd.read_csv(PATH)

array_types = results['Array'].unique()
data_types = results['Type'].unique()
algo_types = results['Algorithm'].unique()

# print table header for the results
init_csv_output()
print_table_header()

for array in array_types:
    for data in data_types:
        data_to_plot = results[(results['Array'] == array) & (results['Type'] == data)]

        # plot
        for algo in algo_types:
            # plot the data with smooth lines
            x = data_to_plot[data_to_plot['Algorithm'] == algo]["Size"]
            y = data_to_plot[data_to_plot['Algorithm'] == algo]["Time"]

            min = y.min()
            max = y.max()
            first_quartile = y.quantile(0.25)
            median = y.quantile(0.5)
            third_quartile = y.quantile(0.75)
            # print the table row
            print_table_row(array, data, algo, min, max, first_quartile, median, third_quartile)
            # save the row to a csv file
            save_csv_row(array, data, algo, min, max, first_quartile, median, third_quartile)

            plt.plot(x, y, label=algo, marker='.')
        
        plt.xscale('log')
        plt.yscale('log')

        plt.xlabel('Size')
        plt.ylabel('Time')
        # first character uppercase
        array_ordering = array.capitalize()
        if array == 'reverse':
            array_ordering = 'Reverse sorted'
        elif array == 'random':
            array_ordering = 'Random generated'

        plt.title(f'{array_ordering} {data} array')
        plt.legend(algo_types, fontsize=10)
        plt.savefig(f'{PATH_TO_PLOTS}/{array}_{data}.png', dpi=600)

        plt.clf()

# print table footer
print_table_footer()
# print the path of the csv file
print('The results are also saved in {}'.format(os.path.relpath(OUT_CSV)))
# print the path of the plots
print('The plots are saved in {}'.format(os.path.relpath(PATH_TO_PLOTS)))



