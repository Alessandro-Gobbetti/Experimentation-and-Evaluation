import sys
import pandas as pd
import matplotlib.pyplot as plt


# read the path from the arguments or use the default path
PATH = sys.argv[1] if len(sys.argv) > 1 else 'Assignment1/results.csv'


results = pd.read_csv(PATH)

array_types = results['Array'].unique()
data_types = results['Type'].unique()
algo_types = results['Algorithm'].unique()


for array in array_types:
    for data in data_types:
        data_to_plot = results[(results['Array'] == array) & (results['Type'] == data)]

        # plot
        for algo in algo_types:
            plt.plot(data_to_plot[data_to_plot['Algorithm'] == algo]["Size"], data_to_plot[data_to_plot['Algorithm'] == algo]["Time"], label=algo)

        plt.xscale('log')
        plt.yscale('log')

        plt.xlabel('Size')
        plt.ylabel('Time')
        plt.title(f'Time vs Size for {array} {data}')
        plt.legend(algo_types, fontsize=10)
        plt.savefig(f'Assignment1/plotting/plots/{array}_{data}.png', dpi=600)
        plt.clf()


