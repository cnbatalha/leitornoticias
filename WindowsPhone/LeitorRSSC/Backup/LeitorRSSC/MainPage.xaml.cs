using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using Microsoft.Phone.Controls;
using System.Xml.Serialization;
using System.Xml;

namespace LeitorRSSC
{
    public partial class MainPage : PhoneApplicationPage
    {

        public List<Categoria> Categorias;

        public Categoria CategoriaSelecionada { get; set; }

        public static Categoria Selecianda = null; 

        // Constructor
        public MainPage()
        {
            InitializeComponent();

            CategoriaSelecionada = new Categoria();

            Categorias = new List<Categoria>();
            Categorias.Add(new Categoria { titulo = "Tecnologia", url = "http://tecnologia.uol.com.br/ultnot/index.xml" });
            Categorias.Add(new Categoria { titulo = "Esporte", url = "http://esporte.uol.com.br/ultimas/index.xml" });
            Categorias.Add(new Categoria { titulo = "Economia", url = "http://rss.uol.com.br/feed/economia.xml" });
        }



        private void btnTecnologia_Click(object sender, RoutedEventArgs e)
        {
            NavigationService.Navigate(new Uri("/pageNoticias.xaml", UriKind.Relative));
        }

        private void LayoutRoot_Loaded(object sender, RoutedEventArgs e)
        {
            lbxCategorias.ItemsSource = Categorias;
        }

        private void txbItemCategoria_Tap_1(object sender, GestureEventArgs e)
        {
            Selecianda = new Categoria();
            Selecianda.titulo = (sender as TextBlock).Text;
            Selecianda.url = (sender as TextBlock).Tag.ToString();

            NavigationService.Navigate(new Uri("/pageNoticias.xaml", UriKind.Relative));
        }
    }
}